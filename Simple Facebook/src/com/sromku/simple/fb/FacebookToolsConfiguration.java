package com.sromku.simple.fb;

import java.util.ArrayList;
import java.util.List;

import com.facebook.SessionDefaultAudience;
import com.facebook.SessionLoginBehavior;

public class FacebookToolsConfiguration
{
	private String mAppId;
	private String mNamespace;
	private List<String> mReadPermissions = null;
	private List<String> mPublishPermissions = null;
	private SessionDefaultAudience mDefaultAudience = SessionDefaultAudience.FRIENDS;
	private SessionLoginBehavior mLoginBehavior = SessionLoginBehavior.SSO_WITH_FALLBACK;
	private boolean mHasPublishPermissions = false;

	private FacebookToolsConfiguration(Builder builder)
	{
		this.mAppId = builder.mAppId;
		this.mNamespace = builder.mNamespace;
		this.mReadPermissions = builder.mReadPermissions;
		this.mPublishPermissions = builder.mPublishPermissions;

		if (this.mPublishPermissions.size() > 0)
		{
			this.mHasPublishPermissions = true;
		}
	}

	/**
	 * Get facebook application id
	 * 
	 * @return
	 */
	String getAppId()
	{
		return mAppId;
	}

	/**
	 * Get application namespace
	 * 
	 * @return
	 */
	String getNamespace()
	{
		return mNamespace;
	}

	/**
	 * Get read permissions
	 * 
	 * @return
	 */
	List<String> getReadPermissions()
	{
		return mReadPermissions;
	}

	/**
	 * Get publish permissions
	 * 
	 * @return
	 */
	List<String> getPublishPermissions()
	{
		return mPublishPermissions;
	}

	/**
	 * Return <code>True</code> if 'PUBLISH' permissions are defined
	 * 
	 * @return
	 */
	boolean hasPublishPermissions()
	{
		return mHasPublishPermissions;
	}

	/**
	 * Get session login behavior
	 * 
	 * @return
	 */
	SessionLoginBehavior getSessionLoginBehavior()
	{
		return mLoginBehavior;
	}

	/**
	 * Get session default audience
	 * 
	 * @return
	 */
	SessionDefaultAudience getSessionDefaultAudience()
	{
		return mDefaultAudience;
	}

	public static class Builder
	{
		private String mAppId = null;
		private String mNamespace = null;
		private List<String> mReadPermissions = new ArrayList<String>();
		private List<String> mPublishPermissions = new ArrayList<String>();

		public Builder()
		{
		}

		/**
		 * Set facebook App Id. <br>
		 * The application id is located in the dashboard of the app in admin panel of facebook
		 * 
		 * @param appId
		 */
		public Builder setAppId(String appId)
		{
			mAppId = appId;
			return this;
		}

		/**
		 * Set application namespace
		 * 
		 * @param namespace
		 * @return
		 */
		public Builder setNamespace(String namespace)
		{
			mNamespace = namespace;
			return this;
		}

		/**
		 * Set the array of permissions you want to use in your application
		 * 
		 * @param permissions
		 */
		public Builder setPermissions(Permissions[] permissions)
		{
			for (Permissions permission: permissions)
			{
				switch (permission.getType())
				{
				case READ:
					mReadPermissions.add(permission.getValue());
					break;
				case PUBLISH:
					mPublishPermissions.add(permission.getValue());
					break;
				default:
					break;
				}
			}

			return this;
		}

		/**
		 * Build the configuration for storage tool.
		 * 
		 * @return
		 */
		public FacebookToolsConfiguration build()
		{
			return new FacebookToolsConfiguration(this);
		}
	}
}
